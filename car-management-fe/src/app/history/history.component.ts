import {Component, OnInit} from '@angular/core';
import {UserService} from "../service/user.service";
import {HistoryDTO} from "../dto/history-dto";
import {ProjectJson} from "../model/project-json";

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {
  historyList: HistoryDTO[] = [];
  teamPage: ProjectJson;
  page: number = 0;

  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
    this.getHistoryCustomer(this.page);
  }

  getHistoryCustomer(page:number) {
    this.userService.getHistory(page).subscribe(next => {
      this.historyList = next.content;
      this.teamPage = next;
      console.log(next)
    })
  }

  changePage(page: number) {
    this.getHistoryCustomer(page);

  }

}
