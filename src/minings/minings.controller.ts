import { Controller, Get, Post, Body, Patch, Param, Delete } from '@nestjs/common';
import { MiningsService } from './minings.service';
import { CreateMiningDto } from './dto/create-mining.dto';
import { UpdateMiningDto } from './dto/update-mining.dto';

@Controller('/minings')
export class MiningsController {
  constructor(private readonly miningsService: MiningsService) {}

  @Post()
  create(@Body() createMiningDto: CreateMiningDto) {
    return this.miningsService.create(createMiningDto);
  }

  @Get('/log')
  findAll() {
    return this.miningsService.findAll();
	}
	
	@Get('/log/:offset/:limit')
	findalloffsetlimit(@Param('offset') offset: string, @Param('limit') limit: string ){
		return this.miningsService.findalloffsetlimit(+offset,+limit)
	}

	@Get('/log/search/:keyword/:offset/:limit')
	search(@Param('keyword') keyword:string, @Param('offset') offset:string , @Param('limit') limit:string){
		return this.miningsService.search(keyword,+offset,+limit)
	}

  @Get(':id')
  findOne(@Param('id') id: string) {
    return this.miningsService.findOne(+id);
  }

  @Patch(':id')
  update(@Param('id') id: string, @Body() updateMiningDto: UpdateMiningDto) {
    return this.miningsService.update(+id, updateMiningDto);
  }

  @Delete(':id')
  remove(@Param('id') id: string) {
    return this.miningsService.remove(+id);
  }
}
