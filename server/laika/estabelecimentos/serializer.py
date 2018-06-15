from rest_framework import serializers
from .models import Estabelecimento,Abrigo,ClinicaVeterinaria
from django.contrib.auth.models import User

class EstabelecimentoSerializer(serializers.Serializer):
    nome = serializers.CharField(max_length=30, required=True)
    tipo_escolhas = serializers.ChoiceField(('Abrigo', 'Clínica Veterinária'), allow_blank=True)
    endereco = serializers.CharField(max_length=60, required=True)
    horFuncionamento = serializers.CharField(max_length=80, required=True)
    telefone = serializers.CharField(max_length=30, required=True)
    email = serializers.CharField(max_length=30, required=True)
    obs = serializers.CharField(max_length=100, required=True)

    def create(self, validated_data):
        return Estabelecimento.objects.create(**validated_data)

    def update(self, instance, validated_data):
        instance.nome = validated_data.get('nome', instance.nome)
        instance.endereco = validated_data.get('endereco', instance.endereco)
        instance.horFuncionamento = validated_data.get('horFuncionamento', instance.horFuncionamento)
        instance.telefone = validated_data.get('telefone', instance.telefone)
        instance.email = validated_data.get('email', instance.email)
        instance.obs = validated_data.get('obs', instance.obs)
        instance.save()

        return instance

class AbrigoSerializer(serializers.Serializer):
    atividades = serializers.CharField(max_length=250, required=True)

    def create(self, validated_data):
        return Abrigo.objects.create(**validated_data)

    def update(self, instance, validated_data):
        instance.nome = validated_data.get('nome', instance.nome)
        instance.endereco = validated_data.get('endereco', instance.endereco)
        instance.horFuncionamento = validated_data.get('horFuncionamento', instance.horFuncionamento)
        instance.telefone = validated_data.get('telefone', instance.telefone)
        instance.email = validated_data.get('email', instance.email)
        instance.obs = validated_data.get('obs', instance.obs)
        instance.atividades = validated_data.get('atividades', instance.atividades)
        instance.save()

        return instance


class ClinicaVeterinariaSerializer(serializers.Serializer):
    veterinarioResp = serializers.CharField(max_length=80, required=True)
    crmvVetResp = serializers.CharField(max_length=15, required=True)
    servicosOferecidos = serializers.CharField(max_length=250, required=True)

    def create(self, validated_data):
        return ClinicaVeterinaria.objects.create(**validated_data)

    def update(self, instance, validated_data):
        instance.nome = validated_data.get('nome', instance.nome)
        instance.endereco = validated_data.get('endereco', instance.endereco)
        instance.horFuncionamento = validated_data.get('horFuncionamento', instance.horFuncionamento)
        instance.telefone = validated_data.get('telefone', instance.telefone)
        instance.email = validated_data.get('email', instance.email)
        instance.obs = validated_data.get('obs', instance.obs)
        instance.atividades = validated_data.get('atividades', instance.atividades)
        instance.veterinarioResp = validated_data.get('veterinarioResp', instance.veterinarioResp)
        instance.crmvVetResp = validated_data.get('crmvVetResp', instance.crmvVetResp)
        instance.servicosOferecidos = validated_data.get('servicosOferecidos', instance.servicosOferecidos)
        instance.save()

        return instance