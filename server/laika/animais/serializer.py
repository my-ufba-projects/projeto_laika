from rest_framework import serializers
from .models import Animal,Cao
from django.contrib.auth.models import User

class AnimalSerializer(serializers.Serializer):
    id = serializers.IntegerField(read_only=True)
    nome = serializers.CharField(max_length=15)
    idade = serializers.IntegerField()
    sexo = serializers.ChoiceField(('Fêmea','Macho','Não identificado'))
    peso = serializers.IntegerField()
    vermifugado = serializers.BooleanField()
    vacinado = serializers.BooleanField()
    descricao = serializers.CharField(max_length=400)
    criador = serializers.ReadOnlyField(source='criador.username')

    def create(self, validated_data):                   #se for para criar, ele usa essa fç
        return Animal.objects.create(**validated_data)

    def update(self, instance, validated_data):         #se for de atualizar, usa essa
        instance.nome = validated_data.get('nome', instance.nome)
        instance.idade = validated_data.get('idade', instance.idade)
        instance.sexo = validated_data.get('sexo', instance.sexo)
        instance.peso = validated_data.get('peso', instance.peso)
        instance.descricao = validated_data.get('descricao', instance.descricao)
        instance.vermifugado = validated_data.get('vermifugado', instance.vermifugado)
        instance.vacinado = validated_data.get('vacinado', instance.vaccinated)
        instance.save()
        return instance


class CaoSerializer(AnimalSerializer):
    porte = serializers.ChoiceField(('Pequeno', 'Médio', 'Grande'))

    def create(self, validated_data):                   #se for para criar, ele usa essa fç
        return Cao.objects.create(**validated_data)

    def update(self, instance, validated_data):         #se for de atualizar, usa essa
        instance.nome = validated_data.get('nome', instance.name)
        instance.idade = validated_data.get('idade', instance.age)
        instance.sexo = validated_data.get('sexo', instance.gender)
        instance.peso = validated_data.get('peso', instance.weight)
        instance.descricao = validated_data.get('descricao', instance.description)
        instance.vermifugado = validated_data.get('vermifugado', instance.dewormed)
        instance.vacinado = validated_data.get('vacinado', instance.vaccinated)
        instance.porte = validated_data.get('porte', instance.size)
        instance.save()
        return instance


class UserAnimalSerializer(serializers.ModelSerializer):
    animais = serializers.PrimaryKeyRelatedField(many=True, queryset=Animal.objects.all())

    class Meta:
        model = User
        fields = ('id', 'username', 'animais')


class UserCaoSerializer(serializers.ModelSerializer):
    caes = serializers.PrimaryKeyRelatedField(many=True, queryset=Cao.objects.all())

    class Meta:
        model = User
        fields = ('id', 'username', 'caes')