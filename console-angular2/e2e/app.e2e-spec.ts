import { ConsoleAngular2Page } from './app.po';

describe('console-angular2 App', () => {
  let page: ConsoleAngular2Page;

  beforeEach(() => {
    page = new ConsoleAngular2Page();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!!');
  });
});
