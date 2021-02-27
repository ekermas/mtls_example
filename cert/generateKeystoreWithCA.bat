del ca.jks
del ca.p12
del ca.crt
del ca.key

del server.jks
del server.p12
del server.csr
del server.crt
del server.cer
del server.key

del ..\nginx\server.crt
del ..\nginx\server.key

keytool -v -genkeypair -dname "CN=Root-CA,OU=Tls,O=Paperbird,C=RU" -keystore ca.jks -storepass secret -keypass secret -keyalg RSA -keysize 2048 -alias root-ca -validity 3650 -deststoretype pkcs12 -ext KeyUsage=digitalSignature,keyCertSign -ext BasicConstraints=ca:true,PathLen:3
keytool -importkeystore -srckeystore ca.jks -srcstorepass secret -deststorepass secret -keypass secret -destkeystore ca.p12 -deststoretype PKCS12
keytool -v -exportcert -file ca.pem -alias root-ca -keystore ca.jks -storepass secret -rfc
openssl pkcs12 -in ca.p12 -nokeys -out ca.crt -password pass:secret -passout pass:secret
openssl pkcs12 -in ca.p12 -nocerts -out ca.key -password pass:secret -passout pass:secret

keytool -v -genkeypair -dname "CN=SERVER,OU=Tls,O=Paperbird,C=RU" -keystore server.jks -storepass secret -keypass secret -keyalg RSA -keysize 2048 -alias server -validity 3650 -deststoretype pkcs12 -ext KeyUsage=digitalSignature,dataEncipherment,keyEncipherment,keyAgreement -ext ExtendedKeyUsage=serverAuth,clientAuth -ext SubjectAlternativeName:c=DNS:localhost,DNS:ekermas-hsrv,IP:127.0.0.1
keytool -v -importcert -file ca.pem -alias root-ca -keystore server.jks -storepass secret -noprompt
keytool -v -certreq -file server.csr -keystore server.jks -alias server -keypass secret -storepass secret -keyalg rsa
keytool -v -gencert -infile server.csr -outfile server.cer -keystore ca.jks -storepass secret -alias root-ca -ext KeyUsage=digitalSignature,dataEncipherment,keyEncipherment,keyAgreement -ext ExtendedKeyUsage=serverAuth,clientAuth -ext SubjectAlternativeName:c=DNS:localhost,DNS:ekermas-hsrv,IP:127.0.0.1
keytool -v -importcert -file server.cer -alias server -keystore server.jks -storepass secret
keytool -importkeystore -srckeystore server.jks -srcstorepass secret -deststorepass secret -keypass secret -destkeystore server.p12 -deststoretype PKCS12
openssl pkcs12 -in server.p12 -nokeys -out server.crt -password pass:secret -passout pass:secret
openssl pkcs12 -in server.p12 -nocerts -out server.key -password pass:secret -passout pass:secret

copy server.crt ..\nginx\server.crt
copy server.key ..\nginx\server.key

REM keytool -v -genkeypair -dname "CN=CLIENT_1,OU=Tls,O=Paperbird,C=RU" -keystore client_1.jks -storepass secret -keypass secret -keyalg RSA -keysize 2048 -alias client_1 -validity 3650 -deststoretype pkcs12 -ext KeyUsage=digitalSignature,dataEncipherment,keyEncipherment,keyAgreement -ext ExtendedKeyUsage=serverAuth,clientAuth
REM keytool -v -certreq -file client_1.csr -keystore client_1.jks -alias client_1 -keypass secret -storepass secret -keyalg rsa
REM keytool -v -gencert -infile client_1.csr -outfile client_1.cer -keystore root-ca/identity.jks -storepass secret -alias root-ca -ext KeyUsage=digitalSignature,dataEncipherment,keyEncipherment,keyAgreement -ext ExtendedKeyUsage=serverAuth,clientAuth
REM keytool -importkeystore -srckeystore client_1/identity.jks -srcstorepass secret -deststorepass secret -keypass secret -destkeystore client_1/identity.p12 -deststoretype PKCS12
REM openssl pkcs12 -in client_1/identity.p12 -nokeys -out client_1/identity.crt -password pass:secret
REM openssl pkcs12 -in client_1/identity.p12 -nocerts -nodes -out client_1/identity.key -password pass:secret
REM openssl pkcs12 -in client_1/identity.p12 -nodes -out client_1/identity.bundle -password pass:secret

