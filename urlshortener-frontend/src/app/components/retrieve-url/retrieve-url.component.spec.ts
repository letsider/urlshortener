import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RetrieveUrlComponent } from './retrieve-url.component';

describe('RetrieveUrlComponent', () => {
  let component: RetrieveUrlComponent;
  let fixture: ComponentFixture<RetrieveUrlComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RetrieveUrlComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RetrieveUrlComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
