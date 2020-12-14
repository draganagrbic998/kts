import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SaveCancelContainerComponent } from './save-cancel-container.component';

describe('SaveCancelContainerComponent', () => {
  let component: SaveCancelContainerComponent;
  let fixture: ComponentFixture<SaveCancelContainerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SaveCancelContainerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SaveCancelContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
