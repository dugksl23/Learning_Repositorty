import { Controller, Get, Post, Body, Patch, Param, Delete } from '@nestjs/common';
import { BridgingsService } from './bridgings.service';
import { CreateBridgingDto } from './dto/create-bridging.dto';
import { UpdateBridgingDto } from './dto/update-bridging.dto';

@Controller('/bridgings')
export class BridgingsController {
  constructor(private readonly bridgingsService: BridgingsService) {}

  @Post()
  create(@Body() createBridgingDto: CreateBridgingDto) {
    return this.bridgingsService.create(createBridgingDto);
  }

  @Get('/log')
  findAll() {
    return this.bridgingsService.findAll();
  }
	@Get('/log/:offset/:limit')
	findalloffsetlimit(@Param('offset') offset: string, @Param('limit') limit:string){
		return this.bridgingsService.findalloffsetlimit(+offset,+limit)
	}

	@Get('/log/search/:keyword/:offset/:limit')
	search(@Param('keyword') keyword:string, @Param('offset') offset:string , @Param('limit') limit:string){
		return this.bridgingsService.search(keyword,+offset,+limit)
	}
	
  @Get(':id')
  findOne(@Param('id') id: string) {
    return this.bridgingsService.findOne(+id);
  }

  @Patch(':id')
  update(@Param('id') id: string, @Body() updateBridgingDto: UpdateBridgingDto) {
    return this.bridgingsService.update(+id, updateBridgingDto);
  }

  @Delete(':id')
  remove(@Param('id') id: string) {
    return this.bridgingsService.remove(+id);
  }
}
