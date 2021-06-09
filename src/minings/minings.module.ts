import { Module } from '@nestjs/common';
import { MiningsService } from './minings.service';
import { MiningsController } from './minings.controller';

@Module({
  controllers: [MiningsController],
  providers: [MiningsService]
})
export class MiningsModule {}
