package com.br.youtubecrawler;

import java.util.ArrayList;
import java.util.List;

import com.br.crawler.domain.Video;

public class main {

	private static List<Video> pagesToVisit = new ArrayList<Video>();

	public static void main(String[] args) {
		Search search = new Search("Depois das Onze");
		List<Video> videos = search.doSearch();

		if (videos != null && !videos.isEmpty()) {
			videos.forEach(video -> {
				System.out.println(video.getComments().size());
				if (video.getComments() != null) {
					video.getComments().forEach(comment -> {
						comment.getAutor().setLikedVideos(
								LikedVideos.getLikedVideos(mountLikedURL(comment.getAutor().getChannel())));
						comment.getAutor().setLikedVideos(FollowedAccounts
								.getFollowedAccounts(mountFollowedURL(comment.getAutor().getChannel())));

					});
				}
			});
		}
	}

	private static String mountLikedURL(String channel) {
		return channel + "/videos?sort=dd&view=15&shelf_id=0";
	}

	private static String mountFollowedURL(String channel) {
		return channel + "/channels?view=56&shelf_id=0";
	}
}
