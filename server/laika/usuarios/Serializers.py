from rest_framework import serializers
from .models import Perfil
from django.contrib.auth.models import User

class UserSerializer(serializers.Serializer):
    id = serializers.IntegerField(read_only=True)
    password = serializers.CharField(write_only=True)
    first_name = serializers.CharField()
    last_name = serializers.CharField()
    email = serializers.CharField()
    username = serializers.CharField()


    class Meta:
        model = User
        fields = ('id','username','first_name', 'last_name', 'email', 'password')

    def create(self, validated_data):
        user = super(UserSerializer, self).create(validated_data)
        user.set_password(validated_data['password'])
        user.save()
        return user

    def update(self, instance, validated_data):
        instance.set_password(validated_data.get('password', instance.password))
        instance.first_name = validated_data.get('first_name', instance.first_name)
        instance.last_name = validated_data.get('last_name', instance.last_name)
        instance.email = validated_data.get('email', instance.email)
        instance.save()
        return instance

class PerfilSerializer (serializers.Serializer):
    id = serializers.IntegerField(read_only=True)
    tipo_conta = serializers.CharField(read_only=True)
    nascimento = serializers.DateField()
    usuario = serializers.ReadOnlyField(source='usuario.username')
    email_primario = serializers.CharField(required=False)
    email_secundario = serializers.CharField(required=False)
    whatsapp = serializers.CharField(required=False)
    facebook = serializers.CharField(required=False)
    instagram = serializers.CharField(required=False)
    telefone = serializers.CharField(required=False)

    def create(self, validated_data):
        return Perfil.objects.create(**validated_data)

    def update(self, instance, validated_data):
        #instance.tipo_conta = validated_data.get('tipo_conta', instance.tipo_conta)
        instance.nascimento = validated_data.get('nascimento', instance.nascimento)
        instance.email_primario = validated_data.get('email_primario', instance.email_primario)
        instance.email_secundario = validated_data.get('email_secundario', instance.email_secundario)
        instance.whatsapp = validated_data.get('whatsapp', instance.whatsapp)
        instance.facebook = validated_data.get('facebook', instance.facebook)
        instance.instagram = validated_data.get('instagram', instance.instagram)
        instance.telefone = validated_data.get('telefone', instance.telefone)
        instance.save()
        return instance
