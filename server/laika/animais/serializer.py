from rest_framework import serializers
from .models import Animal, Cao, Gato
from django.contrib.auth.models import User

class AnimalSerializer(serializers.Serializer):
    id = serializers.IntegerField(read_only=True)
    nome = serializers.CharField(max_length=15, required=True)
    idade = serializers.DateField(allow_null=True,default=None)
    sexo = serializers.ChoiceField(('Fêmea', 'Macho'), allow_blank=True)
    pelagem = serializers.ChoiceField(('Curta', 'Longa'), allow_blank=True)
    peso = serializers.IntegerField(required=False)
    vermifugado = serializers.BooleanField()
    vacinado = serializers.BooleanField()
    descricao = serializers.CharField(max_length=400, allow_blank=True, required=False)
    criador = serializers.ReadOnlyField(source='criador.username')

    def create(self, validated_data):
        return Animal.objects.create(**validated_data)

    def update(self, instance, validated_data):
        instance.nome = validated_data.get('nome', instance.nome)
        instance.idade = validated_data.get('idade', instance.idade)
        instance.sexo = validated_data.get('sexo', instance.sexo)
        instance.pelagem = validated_data.get('pelagem', instance.pelagem)
        instance.peso = validated_data.get('peso', instance.peso)
        instance.descricao = validated_data.get('descricao', instance.descricao)
        instance.vermifugado = validated_data.get('vermifugado', instance.vermifugado)
        instance.vacinado = validated_data.get('vacinado', instance.vaccinated)
        instance.save()
        return instance


class UserAnimalSerializer(serializers.ModelSerializer):
    animais = serializers.PrimaryKeyRelatedField(many=True, queryset=Animal.objects.all())

    class Meta:
        model = User
        fields = ('id', 'username', 'animais')


class CaoSerializer(AnimalSerializer):
    porte = serializers.ChoiceField(('Pequeno', 'Médio', 'Grande'), allow_blank=True)

    def create(self, validated_data):
        return Cao.objects.create(**validated_data)

    def update(self, instance, validated_data):
        instance.nome = validated_data.get('nome', instance.name)
        instance.idade = validated_data.get('idade', instance.age)
        instance.sexo = validated_data.get('sexo', instance.gender)
        instance.pelagem = validated_data.get('pelagem', instance.pelagem)
        instance.peso = validated_data.get('peso', instance.weight)
        instance.descricao = validated_data.get('descricao', instance.description)
        instance.vermifugado = validated_data.get('vermifugado', instance.dewormed)
        instance.vacinado = validated_data.get('vacinado', instance.vaccinated)
        instance.porte = validated_data.get('porte', instance.size)
        instance.save()
        return instance


class UserCaoSerializer(serializers.ModelSerializer):
    caes = serializers.PrimaryKeyRelatedField(many=True, queryset=Cao.objects.all())

    class Meta:
        model = User
        fields = ('id', 'username', 'caes')


class GatoSerializer(AnimalSerializer):

    def create(self, validated_data):
        return Gato.objects.create(**validated_data)

    def update(self, instance, validated_data):
        super.update()


class UserGatoSerializer(serializers.ModelSerializer):
    gatos = serializers.PrimaryKeyRelatedField(many=True, queryset=Gato.objects.all())

    class Meta:
        model = User
        fields = ('id', 'username', 'gatos')