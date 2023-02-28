import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Sprint } from '../interface/sprint';

@Injectable({
  providedIn: 'root',
})
export class SprintService {
  private readonly apiServerUrl = 'http://localhost:8080/api/v1/sprints';

  constructor(private http: HttpClient) {}

  public getAll(): Observable<Sprint[]> {
    return this.http.get<Sprint[]>(`${this.apiServerUrl}`);
  }
}
