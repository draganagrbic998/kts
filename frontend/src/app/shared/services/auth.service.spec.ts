import { TestBed } from '@angular/core/testing';
import { User } from 'src/app/models/user';

import { AuthService } from './auth.service';

describe('AuthService', () => {
  let service: AuthService;
  const mockUser: User = {
    id: 1,
    accessToken: 'token1',
    role: 'role1',
    email: 'email1',
    firstName: 'firstName1',
    lastName: 'lastName1',
    image: 'http://localhost:8080/image1'
  };

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should save user', () => {
    service.saveUser(mockUser);
    const user: User = JSON.parse(localStorage.getItem(service.STORAGE_KEY));
    expect(user).toBeTruthy();
    expect(user.id).toBe(mockUser.id);
    expect(user.accessToken).toBe(mockUser.accessToken);
    expect(user.role).toBe(mockUser.role);
    expect(user.email).toBe(mockUser.email);
    expect(user.firstName).toBe(mockUser.firstName);
    expect(user.lastName).toBe(mockUser.lastName);
    expect(user.image).toBe(mockUser.image);
  });

  it('should delete user', () => {
    service.deleteUser();
    expect(localStorage.getItem('user')).toBeFalsy();
  });

  it('should get user', () => {
    localStorage.setItem(service.STORAGE_KEY, JSON.stringify(mockUser));
    const user: User = service.getUser();
    expect(user).toBeTruthy();
    expect(user.id).toBe(mockUser.id);
    expect(user.accessToken).toBe(mockUser.accessToken);
    expect(user.role).toBe(mockUser.role);
    expect(user.email).toBe(mockUser.email);
    expect(user.firstName).toBe(mockUser.firstName);
    expect(user.lastName).toBe(mockUser.lastName);
    expect(user.image).toBe(mockUser.image);
  });

});
