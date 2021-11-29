
const randomhex=(length: number)=>{
	let result           = '';	var characters       = 'abcdef0123456789';
  for ( var i = 0; i < length; i++ ) {		 result += characters.charAt(Math.floor(Math.random() * length))	}
  return result
}
const randomethaddress=()=>{const length=40
	let result           = '';	var characters       = 'abcdef0123456789';
  for ( var i = 0; i < length; i++ ) {		 result += characters.charAt(Math.floor(Math.random() * length))	}
  return '0x'+result
}

export {randomhex , randomethaddress }
