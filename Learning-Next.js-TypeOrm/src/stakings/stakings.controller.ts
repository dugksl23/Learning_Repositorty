import { Controller, Get, Post, Body, Patch, Param, Delete } from '@nestjs/common';
import { StakingsService } from './stakings.service';
import { CreateStakingDto } from './dto/create-staking.dto';
import { UpdateStakingDto } from './dto/update-staking.dto';

@Controller('/stakings')
export class StakingsController {
  constructor(private readonly stakingsService: StakingsService) {}

  @Post()
  create(@Body() createStakingDto: CreateStakingDto) {
    return this.stakingsService.create(createStakingDto);
  }

  @Get('/log')
  findAll() {
    return this.stakingsService.findAll();
  }

	@Get('/log/:offset/:limit')
	findalloffsetlimit(@Param('offset') offset: string, @Param('limit') limit: string){
		return this.stakingsService.findalloffsetlimit(+offset, +limit)
	}

	@Get('/log/search/:keyword/:offset/:limit')
	search(@Param('keyword') keyword:string, @Param('offset') offset: string, @Param('limit') limit: string){
		return this.stakingsService.search(keyword, +offset, +limit)
	}

  @Get(':id')
  findOne(@Param('id') id: string) {
    return this.stakingsService.findOne(+id);
  }

  @Patch(':id')
  update(@Param('id') id: string, @Body() updateStakingDto: UpdateStakingDto) {
    return this.stakingsService.update(+id, updateStakingDto);
  }

  @Delete(':id')
  remove(@Param('id') id: string) {
    return this.stakingsService.remove(+id);
  }
}
