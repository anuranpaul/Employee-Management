import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatFormField, MatLabel } from '@angular/material/form-field';

@Component({
  selector: 'app-add-employee-dialog',
  standalone: true,
  imports: [MatFormField, MatLabel, ReactiveFormsModule],
  templateUrl: './add-employee-dialog.component.html',
  styleUrl: './add-employee-dialog.component.css'
})
export class AddEmployeeDialogComponent {
  addEmployeeForm: FormGroup;

  constructor(
    public dialogRef: MatDialogRef<AddEmployeeDialogComponent>,
    private fb: FormBuilder
  ) {
    this.addEmployeeForm = this.fb.group({
      employeeId: ['', Validators.required],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      age: ['', [Validators.required, Validators.min(18)]],
      experience: ['', [Validators.required, Validators.min(0)]],
    });
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  onSubmit(): void {
    if (this.addEmployeeForm.valid) {
      this.dialogRef.close(this.addEmployeeForm.value);
    }
  }

  onClose() {
    this.dialogRef.close();
  }
}
