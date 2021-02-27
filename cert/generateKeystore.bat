set OPENSSL_CONF=C:\Program Files (x86)\GnuWin32\share\openssl.cnf

openssl genrsa -des3 -out ca.key -passout pass:secret 4096
openssl req -new -x509 -days 365 -key ca.key -out ca.crt -subj "/C=RU/ST=Moscow/L=Moscow/O=Paperbird/OU=IT/CN=CA" -passin pass:secret -sha256

openssl genrsa -des3 -out server.key -passout pass:secret 4096
openssl req -new -key server.key -out server.csr -subj "/C=RU/ST=Moscow/L=Moscow/O=Paperbird/OU=IT/CN=Server" -config san.conf -passin pass:secret
openssl x509 -req -config ssl.conf -days 365 -in server.csr -CA ca.crt -CAkey ca.key -set_serial 01 -out server.crt -passin pass:secret -sha256
#del server.csr
copy server.crt ..\nginx\server.crt
copy server.key ..\nginx\server.key

openssl genrsa -des3 -out client_01.key -passout pass:secret 4096
openssl req -new -key client_01.key -out client_01.csr -subj "/C=RU/ST=Moscow/L=Moscow/O=Paperbird/OU=IT/CN=Client_01" -passin pass:secret
openssl x509 -req -days 365 -in client_01.csr -CA ca.crt -CAkey ca.key -set_serial 01 -out client_01.crt -passin pass:secret -sha256
#del client_01.csr

