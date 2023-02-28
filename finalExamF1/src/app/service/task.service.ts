import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Task } from '../interface/task';

@Injectable({
  providedIn: 'root',
})
export class TaskService {
  private readonly apiServerUrl = 'http://localhost:8080/api/v1/tasks';

  constructor(private http: HttpClient) {}

  public getAll(): Observable<Task[]> {
    return this.http.get<Task[]>(`${this.apiServerUrl}`);
  }

  public searchAll(
    taskName: string,
    sprintId: number,
    pageNum: number = 0
  ): Observable<Task[]> {
    return this.http.get<Task[]>(
      `${this.apiServerUrl}/search?taskName=${taskName}&sprintId=${sprintId}&pageNum=${pageNum}`
    );
  }

  public deleteTask(taskId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/${taskId}`);
  }

  public saveTask(task: Task): Observable<Task> {
    return this.http.post<Task>(`${this.apiServerUrl}`, task);
  }

  public updateTask(task: Task): Observable<Task> {
    return this.http.put<Task>(`${this.apiServerUrl}/${task.id}`, task);
  }
}
