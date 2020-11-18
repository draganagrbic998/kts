import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ERROR_SNACKBAR_OPTIONS, SUCCESS_SNACKBAR_OPTIONS } from 'src/app/utils/constants';
import { Image } from 'src/app/utils/image';
import { AuthService } from 'src/app/utils/services/auth.service';
import { ImageService } from 'src/app/utils/services/image.service';
import { User } from 'src/app/utils/user';
import { FormValidatorService } from '../../services/form-validator.service';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-profile-form',
  templateUrl: './profile-form.component.html',
  styleUrls: ['./profile-form.component.sass']
})
export class ProfileFormComponent implements OnInit {

  constructor(
    private authService: AuthService,
    private userService: UserService,
    private imageService: ImageService,
    private formValidator: FormValidatorService,
    private snackBar: MatSnackBar
  ) { }

  profile: User = this.authService.getUser();
  savePending: boolean = false;
  image: Image = {path: this.profile.image, upload: null};

  profileForm: FormGroup = new FormGroup({
    email: new FormControl(this.profile.email, [Validators.required, Validators.pattern(new RegExp("\\S"))], 
    [this.formValidator.hasEmail(this.profile.id)]), 
    firstName: new FormControl(this.profile.firstName, [Validators.required, Validators.pattern(new RegExp("\\S"))]), 
    lastName: new FormControl(this.profile.lastName, [Validators.required, Validators.pattern(new RegExp("\\S"))]), 
    
    oldPassword: new FormControl(''),
    newPassword: new FormControl(''), 
    newPasswordConfirmed: new FormControl('')

  }, {
    validators: [this.formValidator.newPasswordConfirmed()]
  });

  addImage(upload: Blob): void{
    this.image.upload = upload;
    this.imageService.getBase64(upload)
    .then((image: string) => {
      this.image.path = image;
    });
  }

  removeImage(): void{
    this.image.upload = null;
    this.image.path = null;
  }

  resetPassword(): void{
    this.profileForm.get('oldPassword').setValue('');
    this.profileForm.get('newPassword').setValue('');
    this.profileForm.get('newPasswordConfirmed').setValue('');
  }

  save(): void{

    if (this.profileForm.invalid){
      return;
    }

    const formData: FormData = new FormData();
    for (const i in this.profileForm.controls){
      if (i === "oldPassword"){
        if (!this.profileForm.get("newPassword").value){
          continue;
        }
      }
      if (i === "newPassword" && !this.profileForm.get("newPassword").value){
        continue;
      }
      if (i === "newPasswordConfirmed"){
        continue;
      }
      formData.append(i, this.profileForm.get(i).value);
    }

    if (this.image.upload){
      formData.append("image", this.image.upload);
    }  

    else if (this.image.path){
      formData.append("imagePath", this.image.path);
    }
    
    this.savePending = true;
    this.userService.update(formData).subscribe(
      (user: User) => {
        this.savePending = false;
        this.resetPassword();
        this.authService.saveUser(user);
        this.snackBar.open("Your profile has been updated!", "Close", SUCCESS_SNACKBAR_OPTIONS);
      }, 
      () => {
        this.savePending = false;
        this.resetPassword();
        this.snackBar.open("An error occured! Try again.", 
        "Close", ERROR_SNACKBAR_OPTIONS);
      }
    )

  }

  ngOnInit(): void {
  }

}
