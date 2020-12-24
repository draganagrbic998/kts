import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatDialogRef } from '@angular/material/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SharedModule } from 'src/app/shared/shared.module';
import { CatTypeDialogComponent } from './cat-type-dialog.component';

describe('CatTypeDialogComponent', () => {
  let component: CatTypeDialogComponent;
  let fixture: ComponentFixture<CatTypeDialogComponent>;

  beforeEach(async () => {
    const dialogRefMock = {};
    await TestBed.configureTestingModule({
      declarations: [ CatTypeDialogComponent ],
      imports: [
        SharedModule,
        BrowserAnimationsModule
      ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
      providers: [
        {provide: MatDialogRef, useValue: dialogRefMock}
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CatTypeDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
