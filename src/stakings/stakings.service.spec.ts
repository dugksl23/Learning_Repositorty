import { Test, TestingModule } from '@nestjs/testing';
import { StakingsService } from './stakings.service';

describe('StakingsService', () => {
  let service: StakingsService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [StakingsService],
    }).compile();

    service = module.get<StakingsService>(StakingsService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });
});
