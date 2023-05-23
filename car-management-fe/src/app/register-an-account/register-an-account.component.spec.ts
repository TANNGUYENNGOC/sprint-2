import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterAnAccountComponent } from './register-an-account.component';

describe('RegisterAnAccountComponent', () => {
  let component: RegisterAnAccountComponent;
  let fixture: ComponentFixture<RegisterAnAccountComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisterAnAccountComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterAnAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
