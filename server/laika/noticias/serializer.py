from rest_framework import serializers
from .models import News
from django.contrib.auth.models import User



class NewsSerializer(serializers.Serializer):
    id = serializers.IntegerField(read_only=True)
    title = serializers.CharField(max_length=30)
    text = serializers.CharField(allow_null=True)
    image = serializers.ImageField(max_length=None, use_url=True, allow_null=True, required=False)
    ntype = serializers.CharField(allow_null=True)
    summary = serializers.CharField(allow_null=True)
    date = serializers.DateField(allow_null=True)

    def create(self, validated_data):
        return News.objects.create(**validated_data)

    def update(self, instance, validated_data):
        instance.newsTitle = validated_data.get('title', instance.title)
        instance.newsText = validated_data.get('text', instance.data)
        instance.save()
        return instance

    class Meta:
        model = News
        fields=('id', 'title', 'text', 'summary', 'image', 'type', 'date')