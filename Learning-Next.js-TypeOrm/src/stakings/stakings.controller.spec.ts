import { Test, TestingModule } from '@nestjs/testing';
import { StakingsController } from './stakings.controller';
import { StakingsService } from './stakings.service';

describe('StakingsController', () => {
  let controller: StakingsController;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      controllers: [StakingsController],
      providers: [StakingsService],
    }).compile();

    controller = module.get<StakingsController>(StakingsController);
  });

  it('should be defined', () => {
    expect(controller).toBeDefined();
  });
});
