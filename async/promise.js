'use strict';

// 1. promise

// promise is a Javascript Object for a asynchronous operation
// - ���ι̽��� �ڹٽ�ũ��Ʈ�� ������Ʈ�̸�, �񵿱����� �۾��� �����ϱ� ���ؼ� ���δ�.
// promise�� js�� object �̱⿡ new Ű���带 �̿��ؼ� ������Ʈ�� �����Ѵ�.
// Promise�� executor ��� �ݹ��Լ��� ��������� �ϸ�, execute �ݹ��Լ���
// resolve�� reject��� �ݹ��Լ��� �޴´�.

// resolve �Լ� : ����� ���������� �����ؼ�, �������� ���� data�� �����Ѵ�.
// rejecte �Լ� : ����� �����ϴٰ�, ������ ���ܼ� ȣ���ϰ� �ȴ�.

// 1-1. index
// state - �۾��� ���� ���� ������� ��Ÿ��
//         pending (ó����)-> fulfiled (�Ϸ�) or rejected(���� ����� ���� �ʰų� data�� ���� ���̴�.)
// producer - promise�� object�̸�, �޾ƿ� data�� ������� ����� ��.
// consumer - �ش� data�� �Һ��ϴ� ��.



// 1-2. Producer
//    when new Promise is created, the Executor runs automatocally

//    promise object�� �����ϴ� ����, execute�� ����ȴٴ� ���̴�.
//    �� promise�� ��������� �� ����, �� �ȿ� network ����� �ڵ尡 �ִٸ� �ﰢ ����ȴ�.

const promise = new Promise((resolve, reject) => {
    // doing some heavy work (network, read files);
    // ��Ʈ��ũ���� �����͸� �޾ƿ��ų�, file���� ���� ū data�� �о���� ������
    // �ð��� �ɸ��� �����̴�.
    // => �̰��� ����� �����ϰ� �Ǹ�, �ش� line�� ó���ϴ���
    // ���� line�� �������� ���ϱ⿡, �񵿱�� ó���ϴ� ���� �Ǵ�.
    console.log('doing something....');
    setTimeout(() => {
        //resolve('ellie') --> then();
        reject(new Error('networking error')) // -> catch();
    }, 2000);
});

// 2. Consumer : then, catch, finally;
// producer���� �������� �����Ͽ�, resolve �ݹ��Լ��� ������ ����,
//  consumer���� then() �Լ��� ���ڷ� �̾�����.

//    2-1. then
promise.then(value => {
    // then�� ���� promise�� return�Ѵ�.
    // �� return �� promise�� catch�� ȣ���� �� �ְ� �ȴ�.
        console.log(value);
    })
    // 2-2 catch
    .catch(value => {
        console.log(value);
    })
    // 2-3 finally
    // promise�� ���������� ����Ǵ� api�̴�.
    .finally(() => {
        console.log('finally');
    });



    // 3. promise chainning 
    //    then api�� ��쿡�� return�� pormise�� �Ѵ�.
    //    ���� promise�� then() or catch()�� ȣ���� �� �ְ� �ȴ�.
    //    �̷��� ����ؼ� promise chain�� �����ȴ�.