import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-update-employee-dialog',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
  ],
  templateUrl: './update-employee-dialog.component.html',
  styleUrl: './update-employee-dialog.component.css',
})
export class UpdateEmployeeDialogComponent {
  updateEmployeeForm: FormGroup;

  constructor(
    public dialogRef: MatDialogRef<UpdateEmployeeDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private fb: FormBuilder
  ) {
    this.updateEmployeeForm = this.fb.group({
      employeeId: [{ value: data.employeeId, disabled: true }],
      firstName: [data.firstName, Validators.required],
      lastName: [data.lastName, Validators.required],
      email: [data.email, [Validators.required, Validators.email]],
      age: [data.age, [Validators.required, Validators.min(18)]],
      experience: [data.experience, [Validators.required, Validators.min(0)]],
    });
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  onSaveClick(): void {
    if (this.updateEmployeeForm.valid) {
      this.dialogRef.close(this.updateEmployeeForm.getRawValue());
    }
  }
}
