import { Test, TestingModule } from '@nestjs/testing';
import { BridgingsService } from './bridgings.service';

describe('BridgingsService', () => {
  let service: BridgingsService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [BridgingsService],
    }).compile();

    service = module.get<BridgingsService>(BridgingsService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });
});
