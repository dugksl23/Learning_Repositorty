import {
  Injectable,
  CanActivate,
  ExecutionContext,
  HttpException,
  HttpStatus,
  UnauthorizedException,
} from '@nestjs/common';
import * as jwt from 'jsonwebtoken';
@Injectable()
export class AuthGuard implements CanActivate {
  async canActivate(context: ExecutionContext): Promise<boolean> {
    const request = context.switchToHttp().getRequest();
    if (!request.headers.athorization === null) {
      return false;
    }

    request.token = await this.validationToken(request.headers.authorization);

    return request;
  }

  async validationToken(auth: string) {
    if (auth.split(' ')[0] !== 'Bearer') {
      throw new HttpException(`Invalid format used`, HttpStatus.FORBIDDEN);
    }

    const token = auth.split(' ')[1];
    try {
      return await jwt.verify(token, process.env.SECRET);
    } catch (err) {
      const errMsg = 'Token error : ' + (err.message || err.name);
      throw new UnauthorizedException('Invalid Token used');
    }
  }
}
