import { TestBed } from '@angular/core/testing';

import { WebsocketService } from './websocket.service';

describe('LobbyService', () => {
  let service: WebsocketService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WebsocketService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
