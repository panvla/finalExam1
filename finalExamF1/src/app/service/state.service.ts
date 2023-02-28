import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { State } from '../interface/state';

@Injectable({
  providedIn: 'root',
})
export class StateService {
  private readonly apiServerUrl = 'http://localhost:8080/api/v1/states';

  constructor(private http: HttpClient) {}

  public getAll(): Observable<State[]> {
    return this.http.get<State[]>(`${this.apiServerUrl}`);
  }
}
