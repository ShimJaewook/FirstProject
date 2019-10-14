# FirstProject

1. 개요
 20142721 심재욱 개인과제입니다.
 총 세 개의 액티비티로 구성되어 있습니다. 각각 로그인, 회원가입, 피아노연주 페이지입니다.
 
2. 로그인 페이지
   MainActivity.java 와 activity_main.xml 파일로 구성되어 있습니다. 기본적인 로그인 페이지이며 입력받은 아이디와 비밀번호를 비교하여 일치시
  피아노 연주 페이지로 넘어갈 수 있습니다. 회원정보는 모바일 기기 로컬에 텍스트 파일로 저장되어 있습니다. 파일 제목이 회원 id, 내용이 비밀번호로
  저장되어 있으며 id를 먼저 찾은 후, 파일 안에 있는 비밀번호와 비교하여 회원여부를 확인합니다. 회원가입 버튼을 누르면 회원가입 페이지로 이동합니다.
  
3. 회원가입 페이지
    sign_up.xml과 SignUp.java 파일로 구성되어 있습니다. 회원정보를 입력 받아 기기 내에 텍스트 파일로 저장합니다. 아이디는 중복확인을 할 수 있으며
   비밀번호는 길이와 특수문자, 대소문자, 숫자의 제약조건을 넣었습니다. 회원가입이 성공하면 다시 로그인 페이지로 넘어갑니다. 뒤로가기 버튼을 누르면
   회원정보가 저장되지 않고 로그인 페이지로 이동합니다.
   
 4. 피아노연주 페이지
     건반을 눌러 피아노를 연주할 수 있는 페이지입니다. Third.java와 activity_third.xml로 구성되어 있으며 피아노 음계는 res.raw에 저장하였습니다.
    핸드폰 기종에 따라 건반이 나오는 양이 달라 넉넉하게 넣었지만 실제 소리가 나오는 음계는 높은 도까지입니다. 로그아웃 버튼을 누르면 로그인 페이지로 
    넘어갑니다.
 
