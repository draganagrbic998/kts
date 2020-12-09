import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ERROR_MESSAGE, ERROR_SNACKBAR_OPTIONS, SNACKBAR_CLOSE, SUCCESS_SNACKBAR_OPTIONS } from 'src/app/constants/dialog';
import { Image } from 'src/app/models/image';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth/auth.service';
import { ImageService } from 'src/app/services/image/image.service';
import { UserService } from 'src/app/services/user/user.service';
import { UserValidatorService } from 'src/app/validators/user/user-validator.service';

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
    private userValidator: UserValidatorService,
    private snackBar: MatSnackBar
  ) { }

  savePending = false;
  profile: User = this.authService.getUser();
  image: Image = {path: this.profile.image, upload: null};

  profileForm: FormGroup = new FormGroup({
    email: new FormControl(this.profile.email, [Validators.required, Validators.pattern(new RegExp('\\S'))],
    [this.userValidator.hasEmail(this.profile.id)]),
    firstName: new FormControl(this.profile.firstName, [Validators.required, Validators.pattern(new RegExp('\\S'))]),
    lastName: new FormControl(this.profile.lastName, [Validators.required, Validators.pattern(new RegExp('\\S'))]),
    oldPassword: new FormControl(''),
    newPassword: new FormControl(''),
    newPasswordConfirmed: new FormControl('')
  }, {
    validators: [this.userValidator.newPasswordConfirmed()]
  });

  changeImage(upload: Blob): void{
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

    for (const key in this.profileForm.value){
      if (key === 'oldPassword' && !this.profileForm.value.newPassword){
        continue;
      }
      if (key === 'newPassword' && !this.profileForm.value.newPassword){
        continue;
      }
      if (key === 'newPasswordConfirmed'){
        continue;
      }
      formData.append(key, this.profileForm.value[key]);
    }

    if (this.image.upload){
      formData.append('image', this.image.upload);
    }

    else if (this.image.path){
      formData.append('imagePath', this.image.path);
    }

    this.savePending = true;
    this.userService.update(formData).subscribe(
      (user: User) => {
        this.savePending = false;
        this.resetPassword();
        this.authService.saveUser(user);
        this.snackBar.open('Your profile has been updated!',
        SNACKBAR_CLOSE, SUCCESS_SNACKBAR_OPTIONS);
      },
      () => {
        this.savePending = false;
        this.resetPassword();
        this.snackBar.open(ERROR_MESSAGE, SNACKBAR_CLOSE, ERROR_SNACKBAR_OPTIONS);
      }
    );

  }

  ngOnInit(): void {
  }

}
