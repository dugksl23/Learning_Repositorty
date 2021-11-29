import { Injectable } from '@nestjs/common';
import { CreateAddressDto } from './dto/create-address.dto';
import { UpdateAddressDto } from './dto/update-address.dto';
import { randomhex, randomethaddress } from '../util/random';

const dummydata = {
  currency: 'LUCA',
  amount: '100',
  address: '0x54bcE5F2BB2aC1B6d1313563d3543d169179CAcf',
  currentstakingamount: '50',
  cumulreceivedamount: '6',
  countstakings: 19,
  countunstakings: 3,
};

@Injectable()
export class AddressesService {
  create(createAddressDto: CreateAddressDto) {
    return 'This action adds a new address';
  }

  findAll() {
    //    return `This action returns all addresses`;
    return { status: 'OK', list: [dummydata] };
  }
  findalloffsetlimit(offset: number, limit: number) {
    //		let dummy={ ... dummydata}; let arr=[]
    //	for (let i=0;i<limit;i++){dummy['address']=randomethaddress(); arr.push(dummy) }
    //return {status:'OK', list:arr }
    return { status: 'OK', list: Array(limit).fill(dummydata) };
  }
  search(keyword: string, offset: number, limit: number) {
    return { status: 'OK', list: Array(limit).fill(dummydata) };
  }
  findOne(id: number) {
    return `This action returns a #${id} address`;
  }

  update(id: number, updateAddressDto: UpdateAddressDto) {
    return `This action updates a #${id} address`;
  }

  remove(id: number) {
    return `This action removes a #${id} address`;
  }
}
