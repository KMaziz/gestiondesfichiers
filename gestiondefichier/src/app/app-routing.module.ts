import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddfileComponent } from './addfile/addfile.component';
import { FileListComponent } from './file-list/file-list.component';


const routes: Routes = [{path: '', redirectTo: 'add', pathMatch: 'full'},
{ path: 'tutorials', component: AddfileComponent },
{ path: 'list', component: FileListComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
