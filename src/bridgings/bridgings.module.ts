import { Module } from '@nestjs/common';
import { BridgingsService } from './bridgings.service';
import { BridgingsController } from './bridgings.controller';

@Module({
  controllers: [BridgingsController],
  providers: [BridgingsService]
})
export class BridgingsModule {}
