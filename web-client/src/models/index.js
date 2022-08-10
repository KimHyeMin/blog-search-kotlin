export class SignUpForm {
    constructor(firstName, lastName, email, password) {
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
        this.email = email.trim();
        this.password = password.trim();
    }

  static init() {
    return new SignUpForm('', '', '', '')
  }
}


export class User {
    constructor(id, name, email) {
      this.id = id;
      this.name = name;
      this.email = email;
    }
}

export class SearchRequest {
  constructor(keywords, page, sort, size, first) {
    this.keywords = keywords;
    this.page = page;
    this.sort = sort
    this.size = size;
    this.first = first;
  }

  static default() {
    return new SearchRequest('', 1, 'ACCURACY', 30, true)
  }
}

