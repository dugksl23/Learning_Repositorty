import { Test, TestingModule } from '@nestjs/testing';
import { MiningsService } from './minings.service';

describe('MiningsService', () => {
  let service: MiningsService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [MiningsService],
    }).compile();

    service = module.get<MiningsService>(MiningsService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });
});
