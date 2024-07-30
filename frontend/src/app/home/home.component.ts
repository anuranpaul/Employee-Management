import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../services/employee.service';
import { CommonModule } from '@angular/common';
import { MatDialog } from '@angular/material/dialog';
import { UpdateEmployeeDialogComponent } from '../update-employee-dialog/update-employee-dialog.component';
import { AddEmployeeDialogComponent } from '../add-employee-dialog/add-employee-dialog.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  employees: any[] = [];
  displayedEmployees: any[] = [];
  currentPage: number = 1;
  itemsPerPage: number = 8;
  totalPages: number = 1;
  searchTerm: string = '';

  constructor(
    private employeeService: EmployeeService,
    private snackBar: MatSnackBar,
    public dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.getAllEmployees();
  }

  getAllEmployees() {
    console.log('Fetching all employees');
    this.employeeService.getAllEmployees().subscribe(
      (response) => {
        console.log('Employees fetched successfully:', response);
        this.employees = response;
        this.totalPages = Math.ceil(this.employees.length / this.itemsPerPage);
        this.updateDisplayedEmployees();
      },
      (error) => {
        console.error('Error fetching the employees', error);
      }
    );
  }

  updateDisplayedEmployees() {
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;
    this.displayedEmployees = this.employees.slice(startIndex, endIndex);
  }

  nextPage() {
    if (this.currentPage < this.totalPages) {
      this.currentPage++;
      this.updateDisplayedEmployees();
    }
  }

  previousPage() {
    if (this.currentPage > 1) {
      this.currentPage--;
      this.updateDisplayedEmployees();
    }
  }

  openAddEmployeeDialog() {
    const dialogRef = this.dialog.open(AddEmployeeDialogComponent, {
      width: '400px',
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        console.log('Adding employee:', result);
        this.employeeService.addEmployee(result).subscribe(
          () => {
            console.log('Employee added successfully');
            this.getAllEmployees();
            this.snackBar.open('Employee added successfully', 'Close', {
              duration: 3000,
            });
          },
          (error) => {
            console.error('Error adding the employee', error);
            this.snackBar.open('Failed to add employee', 'Close', {
              duration: 3000,
            });
          }
        );
      }
    });
  }

  confirmDelete(employeeId: string) {
    if (confirm('Are you sure you want to delete this employee?')) {
      console.log('Deleting employee with ID:', employeeId);
      this.deleteEmployee(employeeId);
    }
  }

  deleteEmployee(employeeId: string): void {
    this.employeeService.deleteEmployee(employeeId).subscribe(
      (response) => {
        console.log('Employee deleted successfully:', response);
        this.employees = this.employees.filter(employee => employee.employeeId !== employeeId);
        this.totalPages = Math.ceil(this.employees.length / this.itemsPerPage);
        this.currentPage = Math.min(this.currentPage, this.totalPages);
        this.updateDisplayedEmployees();
        this.snackBar.open('Employee deleted successfully', 'Close', {
          duration: 3000,
        });
      },
      (error) => {
        console.error('Error deleting the employee', error);
        this.snackBar.open('Failed to delete employee', 'Close', {
          duration: 3000,
        });
      }
    );
  }

  openUpdateDialog(employee: any) {
    const dialogRef = this.dialog.open(UpdateEmployeeDialogComponent, {
      width: '400px',
      data: employee,
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        console.log('Updating employee:', result);
        this.employeeService.updateEmployee(result).subscribe(
          () => {
            console.log('Employee updated successfully');
            this.snackBar.open('Employee updated successfully', 'Close', {
              duration: 3000,
            });
            this.getAllEmployees();
            this.updateDisplayedEmployees();
          },
          (error) => {
            console.error('Error updating the employee', error);
          }
        );
      }
    });
  }

  searchEmployee() {
    if (this.searchTerm.trim() !== '') {
      this.employeeService.getEmployeeById(this.searchTerm).subscribe(
        (response) => {
          console.log('Employee fetched successfully:', response);
          this.employees = [response];
          this.displayedEmployees = [response];
          this.totalPages = 1;
          this.currentPage = 1;
        },
        (error) => {
          console.error('Error fetching the employee', error);
          this.snackBar.open('Employee not found', 'Close', {
            duration: 3000,
          });
          this.employees = [];
          this.displayedEmployees = [];
          this.totalPages = 1;
          this.currentPage = 1;
        }
      );
    } else {
      this.getAllEmployees();
    }
  }
}
