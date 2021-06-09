import { PartialType } from '@nestjs/swagger';
import { CreateStakingDto } from './create-staking.dto';

export class UpdateStakingDto extends PartialType(CreateStakingDto) {}
