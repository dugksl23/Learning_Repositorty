import { Injectable } from '@nestjs/common';
import { CreateMiningDto } from './dto/create-mining.dto';
import { UpdateMiningDto } from './dto/update-mining.dto';

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
export class MiningsService {
  create(createMiningDto: CreateMiningDto) {
    return 'This action adds a new mining';
  }
	findAll() {   // return `This action returns all minings`;
		return {status:'OK', list:[dummydata]}
  }
	findalloffsetlimit(offset:number, limit:number){
		return {status:'OK', list:Array(limit).fill(dummydata)}
	}

	search( keyword:string, offset:number , limit:number){
		return {status:'OK', list:Array(limit).fill(dummydata)} // 
	}

  findOne(id: number) {
    return `This action returns a #${id} mining`;
  }

  update(id: number, updateMiningDto: UpdateMiningDto) {
    return `This action updates a #${id} mining`;
  }

  remove(id: number) {
    return `This action removes a #${id} mining`;
  }
}
