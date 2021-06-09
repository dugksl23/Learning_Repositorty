import { Module } from '@nestjs/common';
import { StakingsService } from './stakings.service';
import { StakingsController } from './stakings.controller';

@Module({
  controllers: [StakingsController],
  providers: [StakingsService]
})
export class StakingsModule {}
