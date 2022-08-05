export class SignUpForm {
    constructor(firstName, secondName, email, password) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.password = password;
    }

  static init() {
    return new SignUpForm('', '', '', '')
  }
}

