import { Injectable } from '@nestjs/common';
import { CreateBridgingDto } from './dto/create-bridging.dto';
import { UpdateBridgingDto } from './dto/update-bridging.dto';

const dummydata={periodstart:'2021-06-08 00:00:00'
, periodend:'2021-06-09 00:00:00'
, periodinhours: 24
, receiveaddresssource: '1Ky9bYskembRNZ9o1Tmm6JYoFvjgjo2DLo'
, feeatsourcechain: '0.001'
, feeatsourceunit: 'BTC'
, feeatdestchain: '0.01'
, feeatdestunit: 'ETH'
, convertedamount: '0.9'
, retainedamount: '0.2'
, receiveaddressdest: '0xfC1074BDe5Bc0c9EF31Df4414cDd53f13621D5fb'
, receivedatetime: '2021-06-09 01:21:17'			
}
@Injectable()
export class BridgingsService {
  create(createBridgingDto: CreateBridgingDto) {
    return 'This action adds a new bridging';
  }

	findAll() { //    return `This action returns all bridgings`;
		return {status:'OK',list:[dummydata]}
  }
	findalloffsetlimit(offset:number,limit:number){
		return {status:'OK',list:Array(limit).fill(dummydata)}
	}
	search( keyword:string, offset:number , limit:number){
		return {status:'OK', list:Array(limit).fill(dummydata)} // 
	}

  findOne(id: number) {
    return `This action returns a #${id} bridging`;
  }

  update(id: number, updateBridgingDto: UpdateBridgingDto) {
    return `This action updates a #${id} bridging`;
  }

  remove(id: number) {
    return `This action removes a #${id} bridging`;
  }
}
