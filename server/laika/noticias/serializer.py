from rest_framework import serializers
from .models import News
from django.contrib.auth.models import User



class NewsSerializer(serializers.Serializer):
    id = serializers.IntegerField(read_only=True)
    newsTitle = serializers.CharField(max_length=30)
    newsText = serializers.CharField()
    newsImage = serializers.ImageField(max_length=None, use_url=True, allow_null=True, required=False)
    newsType = serializers.CharField()

    def create(self, validated_data):
        return News.objects.create(**validated_data)

    def update(self, instance, validated_data):
        instance.newsTitle = validated_data.get('title', instance.title)
        instance.newsText = validated_data.get('text', instance.data)
        instance.save()
        return instance

    class Meta:
        model = News
        fields=('id', 'newsTitle', 'newsText', 'newsImage', 'newsType')