import { PartialType } from '@nestjs/swagger';
import { CreateBridgingDto } from './create-bridging.dto';

export class UpdateBridgingDto extends PartialType(CreateBridgingDto) {}
