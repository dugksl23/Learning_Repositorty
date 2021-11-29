import { Test, TestingModule } from '@nestjs/testing';
import { MiningsController } from './minings.controller';
import { MiningsService } from './minings.service';

describe('MiningsController', () => {
  let controller: MiningsController;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      controllers: [MiningsController],
      providers: [MiningsService],
    }).compile();

    controller = module.get<MiningsController>(MiningsController);
  });

  it('should be defined', () => {
    expect(controller).toBeDefined();
  });
});
