import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LongUrlFormComponent } from './long-url-form.component';

describe('LongUrlFormComponent', () => {
  let component: LongUrlFormComponent;
  let fixture: ComponentFixture<LongUrlFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LongUrlFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LongUrlFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
