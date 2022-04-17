import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientEntityFormComponent } from './client-entity-form.component';

describe('ClientEntityFormComponent', () => {
  let component: ClientEntityFormComponent;
  let fixture: ComponentFixture<ClientEntityFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClientEntityFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientEntityFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
