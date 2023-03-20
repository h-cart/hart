/**
 * 
 */
 
 
 if(!Kakao.isInitialized()){
	Kakao.init('26c618ece5bb5f3d4fd7d428c0f0e7d7');
}

Kakao.Link.sendDefault({
    objectType: 'feed',
    content: {
      title: '함께 장보면 즐거우니까! ',
      description: '내용을 여기에 씁니다',
      imageUrl:
        'https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FeB1Yj7%2Fbtrn8HKdp01%2FlZMtAuvo986os4dCkVoAOk%2Fimg.png',
      imageWidth: 1200,
      imageHeight: 630,
      link: {
        mobileWebUrl: 'https://developers.kakao.com',
        androidExecutionParams: 'test',
      },
    },
    itemContent: {
      profileText: '송송',
      profileImageUrl:
        'https://tistory1.daumcdn.net/tistory/373748/attach/af0ef0205e234b4f9f09d7bce27dd237',
    },
    buttons: [
      {
        title: '블로그 둘러보기',
        link: {
          mobileWebUrl: 'https://songsong.dev',
          webUrl: 'https://songsong.dev',
        },
      },
    ],
  });