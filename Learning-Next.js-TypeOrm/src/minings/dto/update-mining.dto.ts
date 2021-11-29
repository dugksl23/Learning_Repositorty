import { PartialType } from '@nestjs/swagger';
import { CreateMiningDto } from './create-mining.dto';

export class UpdateMiningDto extends PartialType(CreateMiningDto) {}
