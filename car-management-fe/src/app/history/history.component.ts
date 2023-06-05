import {Component, OnInit} from '@angular/core';
import {UserService} from "../service/user.service";
import {HistoryDTO} from "../dto/history-dto";
import {ProjectJson} from "../model/project-json";
import {HistoryDetailDTO} from "../dto/history-detail-dto";

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {
  historyList: HistoryDTO[] = [];
  historyDetailList: HistoryDetailDTO [] = [];
  teamPage: ProjectJson;
  page: number = 0;

  test: string = '';

  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
    this.getHistoryCustomer(this.page);
  }

  getHistoryCustomer(page:number) {
    this.userService.getHistory(page).subscribe(next => {
      this.historyList = next.content;
      this.teamPage = next;
    })
  }

  changePage(page: number) {
    this.getHistoryCustomer(page);

  }


  getHistoryDetail(id: number) {

    this.userService.getHistoryDetail(id).subscribe(next=>{
      this.historyDetailList = next;
      console.log(next)
    })
  }
}
