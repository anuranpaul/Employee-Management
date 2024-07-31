import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private apiUrl = 'http://localhost:9095/api/employee';

  constructor(private http:HttpClient) { }

  addEmployee(employee: any): Observable<any> {
    console.log('Adding employee: ', employee);
    return this.http.post(`${this.apiUrl}/add`, employee);
  }

  getAllEmployees(): Observable<any[]> {
    console.log('Fetching all employees');
    return this.http.get<any[]>(`${this.apiUrl}/view`);
  }

  getEmployeeById(employeeId : string): Observable<any> {
    console.log('Displaying: ', employeeId);
    return this.http.get<any>(`${this.apiUrl}/view/${employeeId}`);
  }

  mergeTest(){
    console.log('Merge test - main ')
  }

  updateEmployee(employee : any){
    console.log('Updating employee: ', employee);
    return this.http.put<any>(`${this.apiUrl}/update`, employee);
  }

  deleteEmployee(employeeId: string): Observable<void> {
    console.log('Deleting employee with id: ', employeeId);
    return this.http.delete<void>(`${this.apiUrl}/delete/${employeeId}`);
  }

}
