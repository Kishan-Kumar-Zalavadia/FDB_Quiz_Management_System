import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Department } from 'src/app/models/departmentModel/department';

@Injectable({
  providedIn: 'root',
})
export class DepartmentService {

  private apiUrl = 'http://localhost:9292/departments'; 

  constructor(private http: HttpClient) {}

  getAllDepartments(): Observable<Department[]> {
    return this.http.get<Department[]>(this.apiUrl);
  }
}
