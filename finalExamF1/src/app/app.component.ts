import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { TaskService } from './service/task.service';
import { Task } from './interface/task';
import { Sprint } from './interface/sprint';
import { SprintService } from './service/sprint.service';
import { StateService } from './service/state.service';
import { State } from './interface/state';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  public states: State[];
  public sprints: Sprint[];
  public tasks: Task[];
  public updateTask: Task;
  public deleteTask: Task;

  constructor(
    private taskService: TaskService,
    private sprintService: SprintService,
    private stateService: StateService
  ) {}

  ngOnInit(): void {
    this.getTasks();
    this.getSprints();
    this.getStates();
  }

  public getTasks(): void {
    this.taskService.getAll().subscribe(
      (response: Task[]) => {
        this.tasks = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeleteTask(): void {
    this.taskService.deleteTask(this.deleteTask.id).subscribe(
      (response: void) => {
        console.log(response);
        this.getTasks();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onAddTask(addForm: NgForm): void {
    document.getElementById('add-task-form').click();
    this.taskService.saveTask(addForm.value).subscribe(
      (response: Task) => {
        console.log(response);
        this.getTasks();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }

  public onUpdateTask(task: Task): void {
    document.getElementById('update-task-form').click();
    this.taskService.updateTask(task).subscribe(
      (response: Task) => {
        console.log(response);
        this.getTasks();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getSprints(): void {
    this.sprintService.getAll().subscribe(
      (response: Sprint[]) => {
        this.sprints = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getStates(): void {
    this.stateService.getAll().subscribe(
      (response: State[]) => {
        this.states = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  onOpenModal(task: Task, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode == 'add') {
      button.setAttribute('data-target', '#addTaskModal');
    }
    if (mode == 'edit') {
      this.updateTask = task;
      console.log(this.updateTask);
      button.setAttribute('data-target', '#updateTaskModal');
    }
    if (mode == 'delete') {
      this.deleteTask = task;
      console.log(this.deleteTask);
      button.setAttribute('data-target', '#deleteTaskModal');
    }
    container.appendChild(button);
    button.click();
  }
}
