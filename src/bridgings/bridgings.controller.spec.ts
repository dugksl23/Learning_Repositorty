import { Test, TestingModule } from '@nestjs/testing';
import { BridgingsController } from './bridgings.controller';
import { BridgingsService } from './bridgings.service';

describe('BridgingsController', () => {
  let controller: BridgingsController;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      controllers: [BridgingsController],
      providers: [BridgingsService],
    }).compile();

    controller = module.get<BridgingsController>(BridgingsController);
  });

  it('should be defined', () => {
    expect(controller).toBeDefined();
  });
});
